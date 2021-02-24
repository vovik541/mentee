<#import "templates/common.ftl" as c>
<@c.page "Users">
<table>
    <#list users as user>
        <tr>
            <th>${user.id}</th>
            <th>${user.name}</th>
            <th>${user.email}</th>
        </tr>
    </#list>
</table>
<a href="/mentee">back</a>
</@c.page>