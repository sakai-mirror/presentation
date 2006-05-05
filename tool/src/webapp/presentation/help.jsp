<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>

<f:loadBundle basename="org.sakaiproject.tool.presentation.bundle.Messages" var="msgs"/>

<f:view>
<sakai:view_container title="#{msgs.pt_title_help}">
<sakai:view_content>
	<h:form>

		<sakai:tool_bar>
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionExit}"
				value="#{msgs.pt_done_button}" />
		</sakai:tool_bar>

			<h:messages showSummary="true" showDetail="true" />
			<sakai:instruction_message value="#{msgs.pt_presentation_help1}" />
			<sakai:instruction_message value="#{msgs.pt_presentation_help2}" />
			<sakai:instruction_message value="#{msgs.pt_presentation_help3}" />
			<sakai:instruction_message value="#{msgs.pt_presentation_help4}" />

	</h:form>

</sakai:view_content>
</sakai:view_container>
</f:view>
