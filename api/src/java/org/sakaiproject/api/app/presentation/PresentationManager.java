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

package org.sakaiproject.api.app.presentation;

import java.util.List;

import org.sakaiproject.exception.IdUnusedException;
import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.exception.TypeException;

/**
 * @author Mark Norton
 */
public interface PresentationManager {

    public boolean allowUpdate(Presentation pres);

    /**
     * Start a slide show on a presentation
     * 
     * @author	Charles Severance
     */
    
    public boolean startShow(Presentation pres);

    /**
     * Stop a slide show on a presentation
     * 
     * @author	Charles Severance
     */
    
    public boolean stopShow(Presentation pres);

    /**
     * Rewind a slide show on a presentation
     * 
     * @author	Charles Severance
     */
    
    public boolean rewindShow(Presentation pres);

    /**
     * Advance a slide show on a presentation
     * 
     * @author	Charles Severance
     */
    
    public boolean advanceShow(Presentation pres);

    /**
     * Reverse a slide show on a presentation
     * 
     * @author	Charles Severance
     */
    
    public boolean backShow(Presentation pres);

    /**
     * Determine if the show is currently showing
     *
     * @author  Charles Severance
     */

    public boolean isShowing(Presentation pres);

    /**
     * Get current slide for a show
     *
     * @author  Charles Severance
     */

    public Slide getCurrentSlide(Presentation pres);

    /**
     * Get current slide number for a show
     *
     * @author  Charles Severance
     */

    public int getCurrentSlideNumber(Presentation pres);

    /**
     * Get the Modification Date as a String
     *
     * @author Charles Severance
     */

    public String getModificationDate(Presentation pres);

    /**
     *  Return an interator which lists all known presentations.
     *
     *  @author Mark Norton
     */
    public List getPresentations()
                throws IdUnusedException, TypeException, PermissionException;

    /**
     * Get a Sakai reference to the presentation area - returns null on 
     * any type of failure.
     *
     *  @author Charles Severance
     */
    public String getHomeReference();

    /**
     * Get a Sakai reference to a particular presentation - returns null on 
     * any type of failure.
     *
     *  @author Charles Severance
     */
    public String getReference(Presentation pres);

    public void clearPresentationCache();
}
