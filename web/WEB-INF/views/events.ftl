<#import "templates/common.ftl" as c>
<@c.page "Events">
    <table>
        <thead>
            <th>Id</th>
            <th>Title</th>
            <th>Date</th>
        </thead>
        <#list events as event>
            <tr>
                <td>${event.id}</td>
                <td>${event.title}</td>
                <td>${event.date?string["dd.MM.yyyy, HH:mm"]}</td>
            </tr>
        </#list>
    </table>
    <a href="/mentee">back</a>
</@c.page>