/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright 2004, 2005, 2006, 2007 Sakai Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
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
 * Presentation.java
 *
 * Created on March 26, 2004, 3:34 PM
 */

package org.sakaiproject.component.app.presentation;
import java.util.List;
import java.util.Vector;

import org.sakaiproject.api.app.presentation.Slide;
import org.sakaiproject.time.api.Time;

/**
 *  The Presentation class allows content to be grouped together in a linear sequence.
 * Any type of presentation control is handled by the presentation manager.
 * @author  Mark Norton
 */
public class PrPresentation implements org.sakaiproject.api.app.presentation.Presentation {
    private String id = null;   //  The Id of this presentation.
    private String title = null;
    private List slides = new Vector();       //  The ordered set of slides in this presentation.
    private Time modificationTime = null;
    
    /** Creates a new instance of Presentation */
    public PrPresentation(String id) {
        this.id = id;
    }

    /** Creates a new instances of Presentation given a list of slides.  */
    public PrPresentation(String id, List slides) {
        this.id = id;
        if (slides != null)
        	this.slides = slides;
    }

    public PrPresentation (String id, List slides, String title, Time time) {
		this.id = id;
		if (slides != null)
			this.slides = slides;
		this.title = title;
		this.modificationTime = time;
	}

    /**
     *  Return the id of this presentation.
     *
     *  @author Mark Norton
     */
    public String getId()  {
        return this.id;
    }

    /**
     *  Set the id of this presentation.
     *
     *  @author Mark Norton
     */
    public void setId (String id)  {
        this.id = id;
    }
	
    public String getTitle () {
		return this.title;
	}

	public void setTitle (String title) {
		this.title = title;
	}

	public String getModificationDate () {
		return this.modificationTime.toStringLocalFull();
	}

    /**
     *  Return an iterator which lists the slides in this presentation.
     *
     *  @author Mark Norton
     */
    public List getSlides() {
		return this.slides;
    }

    /**
     *  Return the slide at the given offset.
     *
     *  @author Mark Norton
     */
    public Slide getSlide (int offset) {
		return (Slide) this.slides.get(offset);
    }

    /**
     *  Append a slide to the end of the slide list.
     *
     *  @author Mark Norton
     */
    public void addSlide(Slide slide) {
        this.slides.add (slide);
    }

    /**
     *  Insert a slide at the position given.
     *
     *  @author Mark Norton
     */
    public void insertSlide(int position, Slide slide) {
        this.slides.add (position, slide);
    }
    
    /**
     *  Return the number of slides in this presentation.
     *
     *  @author Mark Norton
     */
    public int getSlideCount() {
        return this.slides.size();
    }

    /**
     *  Delete the slide at the given position.
     *
     *  @author Mark Norton
     */
    public void deleteSlide(int position)  {
        this.slides.remove(position);
    }

}
