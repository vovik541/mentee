<#import "templates/common.ftl" as c>
<@c.page "Event">
    <table>
        <thead>
            <th>Id</th>
            <th>Title</th>
            <th>Date</th>
        </thead>
        <tr>
            <td>${event.id}</td>
            <td>${event.title}</td>
            <td>${event.date?string["dd.MM.yyyy, HH:mm"]}</td>
        </tr>
    </table>

    <form action="delete" method="post">
        <input name="id" type="text" required pattern="\d+$" placeholder="Id"/>
        <input type="submit" value="Delete"/>
    </form>

    <form action="title">
        <input type="text" name="title" value="Original Title" placeholder="Title"/>
        <input type="submit" value="GetByTitle"/>
    </form>

    <form action="create" method="post">
        <input type="text" name="title" value="Original Title" placeholder="Title"/>
        <input type="date" name="date" value="2021-02-07"/>
        <input type="submit" value="Create Event"/>
    </form>

    <form action="update" method="post">
        <input type="text" name="id" required pattern="\d+$" placeholder="Id"/>
        <input type="text" name="title" value="Original Title" placeholder="Title"/>
        <input type="date" name="date" value="2021-02-07"/>
        <input type="submit" value="Update"/>
    </form>

    <form action="getByDate">
        <input type="date" name="date" value="2021-02-07"/>
        <input name="pageNum" type="text" required pattern="\d+$" placeholder="Page Num"/>
        <input name="pageSize" type="text" required pattern="\d+$" placeholder="Page Size"/>
        <input type="submit" value="Find by Date"/>
    </form>

    <a href="/mentee">back</a>
</@c.page>