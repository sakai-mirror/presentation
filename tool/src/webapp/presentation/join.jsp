<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://sakaiproject.org/jsf/sakai" prefix="sakai" %>

<jsp:useBean id="msgs" class="org.sakaiproject.util.ResourceLoader" scope="session"> 
   <jsp:setProperty name="msgs" property="baseName" value="org.sakaiproject.tool.presentation.bundle.Messages"/> 
</jsp:useBean>

<f:view>
<sakai:view_container title="#{msgs.pt_title_view}">
<sakai:view_content>
	<h:form>
		<sakai:tool_bar>
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionExit}"
				value="#{msgs.pt_exit_button}" />
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionRefresh}"
				value="#{msgs.pt_refresh_button}" />
			<sakai:tool_bar_item
				action="#{PresentationTool.processActionView}"
				value="#{msgs.pt_view_button}" />
			<sakai:tool_bar_spacer />
			<sakai:tool_bar_item
				disabled="true"
				value="Watching slide: #{PresentationTool.showPosition}" 
			/>
		</sakai:tool_bar>
                <sakai:messages />
                <sakai:instruction_message value="#{PresentationTool.instructionMessage}"/>
                <f:verbatim>
                       <iframe src="${PresentationTool.slide.url}" width="100%" height="1200px">
                       </iframe>
                </f:verbatim>
	</h:form>
<sakai:courier refresh="5"/>
</sakai:view_content>
</sakai:view_container>
</f:view>
