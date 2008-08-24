/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2004, 2005, 2006 Sakai Foundation
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
 * Created on March 26, 2004, 3:17 PM
 */

package org.sakaiproject.api.app.presentation;
import java.io.Serializable;

/**
 *
 * @author  Mark Norton
 *
 *  A slide is a single page of a presentation.  Each slide has a type, which usually
 *  will be consistent throughout a presentation, but is not required to be so.  Each
 *  slide may have an optional displayName.
 */
public interface Slide extends java.io.Serializable {

	/**
	 * 	Return the Url for this slide.
	 * 	@return url of this slide.
	 */
	public String getUrl();
	
	/**
	 * 	Set the url for this slide.
	 * 	@param url
	 */
	public void setUrl(String url);
	
    /**
     *  Return the content associated with this slide.
     *
     *  @author Mark Norton
     */
    public Serializable getContent();

    /**
     *  Set the content associated with this slide.
     *
     *  @author Mark Norton
     */
    public void setContent(Serializable content);

    /**
     *  Get the display name (title) of this slide.
     *
     *  @author Mark Norton
     */
    public String getDisplayName();

    /**
     *  Set the display name (title) of this slide.
     *
     *  @author Mark Norton
     */
    public void setDisplayName(String name);

    /**
     *  Get the slide type.  Slide type determines which kind of content
     *  is included in this slide.
     *
     *  @author Mark Norton
     */
    public String getType();

    /**
     *  Set the slide type.  Slide type identifies the kind of content
     *  included in this slide.
     *
     *  @author Mark Norton
     */
    public void setType(String type);

}

/**********************************************************************************
*
* $Footer:  $
*
***********************************************************************************/

