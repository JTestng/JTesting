<!DOCTYPE html PUBh2C "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h1>${test.title}</h1>
	<#list test.questions>
		<form action="/submit" method="POST">
		<#items as question>
		<#if question.type == "MULTIPLE_CHOICE">
			<h2> Multiple Choice </h2>
			<#list question.answers as answer>
			<input type="radio" name="q" value="v1"/>
			</#list>
		<#else>
			<#if question.type == "TRUE_FALSE">
				<h2> True Fase </h2>
			<#else>
				<h2> Error: Unknown question type </h2>
			</#if>
		</#if>
		</#items>
		</form>
	<#else>
		<p> Error: Empty test bank </p>
	</#list>
</body>
</html>
