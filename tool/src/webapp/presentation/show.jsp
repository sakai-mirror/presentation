<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>

<jsp:useBean id="msgs" class="org.sakaiproject.util.ResourceLoader" scope="session"> 
   <jsp:setProperty name="msgs" property="baseName" value="org.sakaiproject.tool.presentation.bundle.Messages"/> 
</jsp:useBean>

<f:view>
<sakai:view_container title="#{msgs.pt_title_show}">
<sakai:view_content>
	<h:form>
		<sakai:tool_bar>
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionEnd}"
				value="#{msgs.pt_stop_button}" />
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionPrevious}"
                                disabled="#{not PresentationTool.hasPrevious}"
				value="#{msgs.pt_previous_button}" />
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionNext}"
                                disabled="#{not PresentationTool.hasNext}"
				value="#{msgs.pt_next_button}" />
			<sakai:tool_bar_spacer />
			<sakai:tool_bar_item
				disabled="true"
				value="Presenting slide: #{PresentationTool.showPosition}" 
			/>
		</sakai:tool_bar>
		<sakai:messages />
		<h4><h:outputText value="#{PresentationTool.slide.displayName}" /></h4>
		<sakai:instruction_message value="#{PresentationTool.instructionMessage}"/>
                <f:verbatim>
                     <iframe src="${PresentationTool.slide.url}" width="100%" height="1200px">
		     </iframe>
                </f:verbatim>
	</h:form>
</sakai:view_content>
</sakai:view_container>
</f:view>
