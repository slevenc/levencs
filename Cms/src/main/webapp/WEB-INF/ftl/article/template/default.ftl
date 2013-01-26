<html>
<#if article?? >
<head>
<title>${article.title}</title>
</head>
<body>
<h1>
${article.title}
</h1>
<#assign dt = article.createDate?datetime>
<h2>发表于${dt} 由 ${article.author}</h2>
<div class="articleBody">
${article.content}
</div>
</#if>
</body>
</html>