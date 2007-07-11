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
				action="#{PresentationTool.processActionExit}"
				value="#{msgs.pt_done_button}" />
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionPrevious}"
				value="#{msgs.pt_previous_button}" />
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionNext}"
				value="#{msgs.pt_next_button}" />
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionJoin}"
				rendered="#{PresentationTool.isShowing}"
				value="#{msgs.pt_join_button}" />
			<sakai:tool_bar_spacer />
			<sakai:tool_bar_item
				disabled="true"
				value="Viewing slide: #{PresentationTool.showPosition}" 
			/>
		</sakai:tool_bar>
            	<sakai:messages />
             <sakai:instruction_message value="#{PresentationTool.instructionMessage}"/>
			<h:graphicImage value="#{PresentationTool.slide.url}" />
	</h:form>
</sakai:view_content>
</sakai:view_container>
</f:view>
