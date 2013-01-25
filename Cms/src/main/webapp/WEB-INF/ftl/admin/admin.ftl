<html>


<body>

<table>

<#list systemProperties as p>
          <tr>
          <td>
        ${p.name}
        </td>
        <td>
        ${p.value}
        </td>
        </tr>

</#list>

</table>


</body>

</html>