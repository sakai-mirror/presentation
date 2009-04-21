/**********************************************************************************
 * $URL$
 * $Id$
 ***********************************************************************************
 *
 * Copyright (c) 2004, 2005, 2006, 2008 The Sakai Foundation
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
 * Slide.java
 *
 * Created on March 26, 2004, 3:17 PM
 */

package org.sakaiproject.component.app.presentation;
import java.io.Serializable;

/**
 *
 * @author  Mark Norton
 *
 *  A slide is a single page of a presentation.  Each slide has a type, which usually
 *  will be consistent throughout a presentation, but is not required to be so.  Each
 *  slide may have an optional displayName.
 */
public class PrSlide implements org.sakaiproject.api.app.presentation.Slide{

	private String url = null;

    private Serializable content = null;

    private String displayName = null;

    private String contentType = null;

    /** Creates a new Slide given content and type */
    public PrSlide(Serializable content, String type) {
        this.content = content;
        this.contentType = type;
    }

    /**	Creates a new Slide given url, name, and type.  */
    public PrSlide (String url, String name, String type) {
    	this.url = url;
    	this.displayName = name;
    	this.contentType = type;
    }

    /**
     * 	Get the URL for this slide.
     * 	@return slide URL.
     */
    public String getUrl () {
    	return this.url;
    }

    /**
     * 	Set the URL for this slide.
     * 	@param url
     */
    public void setUrl (String url) {
    		this.url = url;
    }

    /**
     *  Return the content of this slide.
     *
     *  @author Mark Norton
     */
    public Serializable getContent() {
        return this.content;
    }

    /**
     *  Set the content of this slide.
     *
     *  @author Mark Norton
     */
    public void setContent(Serializable content) {
        this.content = content;
    }

    /**
     *  Get the display name (title) of this slide.
     *
     *  @author Mark Norton
     */
    public String getDisplayName() {
        return this.displayName;
    }

    /**
     *  Set the display name of this slide.
     *
     *  @author Mark Norton
     */
    public void setDisplayName(String name) {
        this.displayName = name;
    }

    /**
     *  Get the content type of this slide.
     *
     *  @author Mark Norton
     */
    public String getType() {
        return this.contentType;
    }

    /**
     *  Set the content type of this slide.
     *
     *  @author Mark Norton
     */
    public void setType(String type) {
        this.contentType = type;
    }

}
