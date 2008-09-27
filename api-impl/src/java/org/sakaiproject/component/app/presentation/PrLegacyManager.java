/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2004, 2005, 2006, 2007 Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 **********************************************************************************/

/*
 * PresentationManager.java
 *
 * Created on March 26, 2004, 3:58 PM
 */

package org.sakaiproject.component.app.presentation;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import org.sakaiproject.api.app.presentation.Presentation;
import org.sakaiproject.api.app.presentation.Slide;
import org.sakaiproject.content.api.ContentCollection;
import org.sakaiproject.content.api.ContentCollectionEdit;
import org.sakaiproject.content.cover.ContentHostingService;
import org.sakaiproject.entity.api.Entity;
import org.sakaiproject.entity.api.EntityPropertyNotDefinedException;
import org.sakaiproject.entity.api.EntityPropertyTypeException;
import org.sakaiproject.entity.api.ResourceProperties;
import org.sakaiproject.entity.api.ResourcePropertiesEdit;
import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.exception.InUseException;
import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.exception.TypeException;
import org.sakaiproject.time.api.Time;
import org.sakaiproject.tool.cover.ToolManager;

/**
 *
 * @author  Mark Norton
 *
 *  The PresentationManager allows new presentations to be created and added.  It
 *  provides a means to list all known presentations via a PresentationIterator.
 */
public class PrLegacyManager implements org.sakaiproject.api.app.presentation.PresentationManager {
	
	public static final String PROP_PRESENT_CURRENT_SLIDE = "Presentation:Current-Slide-Id";

    /*	Service Dependency:  Logger - eventually this will be the Sakai Logger.  */
    public static Logger logger = null;
    
    /** Clear out any cached presentations 
     * 
     * @author Charles Severance
     * 
     */
    public void clearPresentationCache()
	{
    		// Not currently needed - there is no cache
    		// System.out.println("Presentation cache cleared");
	}
    
    /**
     * Substitute for getting a context.  This is the Site string.
     */
    private String getContext()
    {
    		String retval = ToolManager.getCurrentPlacement().getContext();
	    // System.out.println("Context="+retval);
	    return retval;
    }
    
    /**
     * Get the current "home" collection
     */
    private String getHomeCollection()
    {
       	String retval = ContentHostingService.getSiteCollection(getContext());
       	// System.out.println("Home Collection = "+retval);
       	return retval;
    }
    
    public String getHomeReference()
    {
    		String homeCollection = getHomeCollection();
    		// System.out.println("Home Collection = "+homeCollection);
    		if ( homeCollection == null ) return null;
    		
    		String refString = ContentHostingService.getReference(homeCollection);
    		// System.out.println("RefString="+refString);
    		return refString;
    	}

    public String getReference(Presentation pres)
    {
    		String presId = getPresentationIdString(pres);
    		// System.out.println("Presentation Id = "+presId);
    		if ( presId == null ) return null;
    		
    		String refString = ContentHostingService.getReference(presId);
    		// System.out.println("RefString="+refString);
    		return refString;
    	}

    /**
     * This is just a utility function to eat exceptions and do 
     * common error checking.
     */
    private String getPresentationIdString(Presentation pres)
    {
		
		String presId = null;
		if ( pres == null ) 
		{
			// System.out.println("getPresentationIdString - Presentation is null!");
			return null;
		}
		
		try 
		{ 
			presId = pres.getId(); 
		} 
		catch(Throwable t) 
		{
			presId = null;
		}
		return presId;
    }
    
    /**
     * Determine if the user is allowed to modify this presentation
     * 
     * @author	Charles Severance
     */
    
    public boolean allowUpdate(Presentation pres)
    {
    		boolean retval = false;
    		
		String presId = getPresentationIdString(pres);
		if ( presId == null ) return false;
		
		retval = ContentHostingService.allowUpdateCollection(presId);
		
		// System.out.println("allowUpdate:"+presId+" returns "+retval);
    		return retval;
    }
    
    public String getTitle(Presentation pres)
    {
  
    		String retval = null;
        	String presId = getPresentationIdString(pres);
        	if ( presId == null ) return retval;
        		
        	try
    		{
       		// System.out.println("getModificationDate:"+presId);
       		ContentCollection collection = ContentHostingService.getCollection(presId);
    			
       		if ( collection == null ) return null;
       		return getTitle(collection);
    		}
        	catch (IdUnusedException e)
    		{
    			// System.out.println("IdUnusedException.");
    			return null;
    		}
    		catch (TypeException e)
    		{
    			// System.out.println("TypeException.");
    			return null;
    		}
    		catch (PermissionException e)
    		{
    			// System.out.println("PermissionException");
    			return null;
    		}
    }
    
    private String getTitle(ContentCollection collection)
    {
    		String retval = null;
    		if ( collection == null ) return null;
    		
       	ResourceProperties props = collection.getProperties();
       	retval  = props.getProperty(ResourceProperties.PROP_DISPLAY_NAME);
    		// System.out.println("Returning title "+retval);
    		return retval;
    }
    
    /**
     * Gets the modification time of the presentation - always goes through to the 
     * Content service so as to get the live values.
     * 
     * @param pres
     * @return
     */
    public Time getModificationTime(Presentation pres)
    {

    		Time retval = null;
        	String presId = getPresentationIdString(pres);
        	if ( presId == null ) return retval;
        		
        	try
    		{
       		// System.out.println("getModificationDate:"+presId);
       		ContentCollection collection = ContentHostingService.getCollection(presId);
    			
       		if ( collection == null ) return null;
			
       		ResourceProperties props = collection.getProperties();
       		retval  = props.getTimeProperty(ResourceProperties.PROP_MODIFIED_DATE);
    			// System.out.println("Returning "+retval);
    			return retval;
    		}
    		catch (EntityPropertyNotDefinedException e)
    		{
    			// System.out.println("EntityPropertyNotDefinedException.");
    			return null;
    		}
        	catch (IdUnusedException e)
    		{
    			// System.out.println("IdUnusedException.");
    			return null;
    		}
    		catch (EntityPropertyTypeException e)
    		{
    			// System.out.println("TypeException.");
    			return null;
    		}
    		catch (TypeException e)
    		{
    			// System.out.println("TypeException.");
    			return null;
    		}
    		catch (PermissionException e)
    		{
    			// System.out.println("PermissionException");
    			return null;
    		}
    }
    
    /**
     * Gets the modification time of the presentation - always goes through to the 
     * Content service so as to get the live values.
     * 
     * @param pres
     * @return
     */
    private Time getModificationTime(ContentCollection collection)
    {

    		Time retval = null;
    		if ( collection == null ) return null;
 
        	try
    		{		
       		ResourceProperties props = collection.getProperties();
       		retval  = props.getTimeProperty(ResourceProperties.PROP_MODIFIED_DATE);
    			// System.out.println("Returning "+retval);
    			return retval;
    		}
    		catch (EntityPropertyNotDefinedException e)
    		{
    			// System.out.println("EntityPropertyNotDefinedException.");
    			return null;
    		}
    		catch (EntityPropertyTypeException e)
    		{
    			// System.out.println("EntityPropertyNotDefinedException.");
    			return null;
    		}
    }
    
    /**
     * Gets the modification date of the presentation - always goes through to the 
     * Content service so as to get the live values.
     * 
     * @param pres
     * @return
     */
    public String getModificationDate(Presentation pres)
    {
    		Time modTime = getModificationTime(pres);
    		
    		return modTime.toStringLocal();
    }
    
    /**
     * Start a slide show on a presentation
     * 
     * @author	Charles Severance
     */
    
    public boolean startShow(Presentation pres)
    {
    		// System.out.println("startShow:"+pres);
    		return setCurrentSlideProperty(pres, 0);
    }

    /**
     * Stop a slide show on a presentation
     * 
     * @author	Charles Severance
     */
    public boolean stopShow(Presentation pres)
    {
    		// System.out.println("stopShow:"+pres);
		return setCurrentSlideProperty(pres, -1);
    }
    
    public boolean rewindShow(Presentation pres)
    {
		return setCurrentSlideProperty(pres, 0);
    }

    /**
     * Advance a slide show on a presentation
     * 
     * @author  Charles Severance
     */
   
    public boolean advanceShow(Presentation pres)
    {
    		// System.out.println("advanceShow:"+pres);
    		if ( pres == null ) return false;
  
    		int currSlide = getCurrentSlideProperty(pres);
 
        currSlide ++;

        if ( currSlide > pres.getSlideCount() - 1) {
        		currSlide = pres.getSlideCount() - 1;
        }
        if ( currSlide < 0 ) currSlide = 0;
 
        return setCurrentSlideProperty(pres, currSlide);
    }
    
    /**
     * Reverse a slide show on a presentation
     * 
     * @author  Charles Severance
     */
   
    public boolean backShow(Presentation pres)
    {
        if ( pres == null ) return false;
        
        int currSlide = getCurrentSlideProperty(pres);
        
        currSlide --;

        if ( currSlide > pres.getSlideCount() - 1) {
        		currSlide = pres.getSlideCount() - 1;

        }
        if ( currSlide < 0 ) currSlide = 0;
        return setCurrentSlideProperty(pres, currSlide);
    }
    
    /** 
     * Returns true if an active show is going on for the presentation 
     * 
     * @author Charles Severance
     */
    
    public boolean isShowing(Presentation pres)
    {
        if ( pres == null ) return false;
		int currSlide = getCurrentSlideProperty(pres);
		return (currSlide >= 0);
    }
    
    /**
     * Get current slide for a show
     *
     * @author  Charles Severance
     */

    public Slide getCurrentSlide(Presentation pres)
    {
        if ( pres == null ) return null;

		int currSlide = getCurrentSlideNumber(pres);
    		
    		return pres.getSlide(currSlide);
    }
    
    public int getCurrentSlideNumber(Presentation pres)
    {
        if ( pres == null ) return -1;

		int currSlide = getCurrentSlideProperty(pres);
    		if ( currSlide > pres.getSlideCount() -1 ) currSlide = pres.getSlideCount() - 1;
    		if ( currSlide < 0 ) currSlide = 0;
    		
    		return currSlide;
    }
    
    /** Get the current property for the current slide - return -1 if not there */
    private int getCurrentSlideProperty(Presentation pres)
    {
    		String presId = getPresentationIdString(pres);
    		if ( presId == null ) return 0;

       	try
		{
   			// System.out.println("getCurrentSlideProperty:"+presId);
   			ContentCollection collection = ContentHostingService.getCollection(presId);
			
   			return getCurrentSlideProperty(collection);
		}
       	catch (IdUnusedException e)
		{
			// System.out.println("IdUnusedException.");
			return -1;
		}
		catch (TypeException e)
		{
			// System.out.println("TypeException.");
			return -1;
		}
		catch (PermissionException e)
		{
			// System.out.println("PermissionException");
			return -1;
		}    	
    }
    
    /** Get the current property for the current slide - return -1 if not there */
    private int getCurrentSlideProperty(ContentCollection collection)
    {
    		if ( collection == null ) return -1;

       	try
		{
     		ResourceProperties props = collection.getProperties();
			long longval = props.getLongProperty(PROP_PRESENT_CURRENT_SLIDE);
			// System.out.println("Property long = "+longval);
			int retval = (int) longval;
			return retval;
		}
		catch (EntityPropertyNotDefinedException e)
		{
			// Normal path
			// System.out.println("EntityPropertyNotDefinedException.");
			return -1;
		}   	
		catch (EntityPropertyTypeException e)
		{
			// System.out.println("TypeException.");
			return -1;
		}   
    }

    private boolean setCurrentSlideProperty(Presentation pres, int slideno)
    {
		String presId = getPresentationIdString(pres);
		if ( presId == null ) return false;
		
    		// System.out.println("setCurrentSlideProperty slideno="+slideno+" id="+presId);
    		try
		{
//			ContentCollection collection = ContentHostingService.getCollection(presId);
					
			ContentCollectionEdit cedit = null;
			ResourcePropertiesEdit pedit = null;
			if (ContentHostingService.getProperties(presId).getBooleanProperty(ResourceProperties.PROP_IS_COLLECTION))
			{
				cedit = ContentHostingService.editCollection(presId);
				pedit = cedit.getPropertiesEdit();
			}
			else
			{
				return false;
			}
			if ( slideno <= -1 ) {
				// System.out.println("Removed property"+PROP_PRESENT_CURRENT_SLIDE);
				pedit.removeProperty (PROP_PRESENT_CURRENT_SLIDE);
			}
			else
			{
				pedit.addProperty (PROP_PRESENT_CURRENT_SLIDE, ""+slideno);
			}
			ContentHostingService.commitCollection(cedit);
			// System.out.println("Commit Happenned");
			return true;
		}
		catch (IdUnusedException e)
		{
			// System.out.println("IdUnusedException.");
		}
		catch (EntityPropertyTypeException e)
		{
			// System.out.println("TypeException.");
		}
		catch (TypeException e)
		{
			// System.out.println("TypeException.");
		}
		catch (PermissionException e)
		{
			// System.out.println("PermissionException");
		}
		catch (InUseException e)
		{
			// System.out.println("InUseException");
		}    		
		catch (EntityPropertyNotDefinedException e)
		{
			// System.out.println("EntityPropertyNotDefinedException");
		}
		return false;
    }
    
    /**
     *  Return an interator which lists all known presentations.
     *
     *  @author Mark Norton
     */
    public List getPresentations() 
    		throws IdUnusedException, TypeException, PermissionException
	{
//    		String context = getContext();
    		
    		List presentations = new Vector();
       	String home = getHomeCollection();
    	    String collectionId = home + "Presentations/";
    	    
		// Get the collection and throw an exception upwards if there is a problem
		// System.out.println("Getting Presentations From:"+collectionId);
		ContentCollection collection = ContentHostingService.getCollection(collectionId);
		List newMembers = collection.getMemberResources ();
		// String sortedBy = ResourceProperties.PROP_DISPLAY_NAME;
		String sortedBy = ResourceProperties.PROP_MODIFIED_DATE;
		Collections.sort (newMembers, ContentHostingService.newContentHostingComparator (sortedBy, false));
		int size = newMembers.size();
//		Hashtable moreMembers = new Hashtable();
		// System.out.println("Size: "+size);
		for (int i = 0; i< size; i++)
		{		
	    	    try
			{
				Entity resource = (Entity) newMembers.get(i);
				String nextId = resource.getId();
				// System.out.println("Next ID = "+nextId);
				boolean isCollection = resource.getProperties().getBooleanProperty(ResourceProperties.PROP_IS_COLLECTION);
	
				if (isCollection)
				{
					Presentation xpres = loadPresentation(nextId);
			        if ( xpres != null && xpres.getSlideCount() > 0 ) presentations.add (xpres);
				}

			}
			catch (EntityPropertyTypeException e)
			{
				// System.out.println("TypeException.");
				continue;
			} 
			catch (EntityPropertyNotDefinedException e) 
			{
				// System.out.println("EntityPropertyNotDefinedException");
				continue;
			}
		}
		
    		return presentations;
    }
    
    /*
     * 	Load a presentation given it's repository directory.
     */
    private Presentation loadPresentation(String presId) {
		// System.out.println("loadPresentaton:"+presId);

    	String id = null;
    		try { id = presId;  } catch (Throwable t) {return null;}
        Presentation pres = null;
   		try
		{
			ContentCollection collection = ContentHostingService.getCollection(presId);
	
   			List slides = null;
   			String title = getTitle(collection);
   			Time modificationTime = getModificationTime(collection);
 
   			pres = (Presentation) new PrPresentation(id, slides, title, modificationTime);

   			List colMembers = collection.getMemberResources ();

			String sortedBy = ResourceProperties.PROP_DISPLAY_NAME;
			Collections.sort (colMembers, ContentHostingService.newContentHostingComparator (sortedBy, true));
			// System.out.println("Sorted...");
		    int colsize = colMembers.size();
		    // System.out.println("Sub coll size "+colsize);

			for (int j = 0; j< colsize; j++)
			{
				Entity subResource = (Entity) colMembers.get(j);
				// System.out.println("   Next ID = "+subResource.getId());
				// System.out.println("   Next URL = "+subResource.getUrl());	

				ResourceProperties resP = subResource.getProperties();
	    			String slideName = (String) resP.get(resP.getNamePropDescription());
	    			String slideType = "SlideType";
	    			String slideUrl = subResource.getUrl();
	    		
	    			//  Create the slide and add it to the presentation.
	    			Slide slide = new PrSlide (slideUrl, slideName, slideType);
	    			pres.addSlide(slide);
			}

			// System.out.println("Done adding slides...");
		}
		catch (IdUnusedException e)
		{
			// System.out.println("IdUnusedException.");
		}
		catch (TypeException e)
		{
			// System.out.println("TypeException.");
		}
		catch (PermissionException e)
		{
			// System.out.println("PermissionException");
		}

        return pres;
    }
}
