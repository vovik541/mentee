<#import "templates/common.ftl" as c>
<@c.page "User">
    <table>
        <tr>
            <th>${user.id}</th>
            <th>${user.name}</th>
            <th>${user.email}</th>
        </tr>
    </table>

    <form action="delete" method="post">
        <input name="id" type="text" required pattern="\d+$" placeholder="Id"/>
        <input type="submit" value="Delete"/>
    </form>

    <form action="update" method="post">
        <input name="id" type="text" required pattern="\d+$" placeholder="Id"/>
        <input name="name" type="text" required pattern="\w+" placeholder="Name"/>
        <input name="email" type="email" placeholder="Email"/>
        <input type="submit" value="Update"/>
    </form>

    <form action="create" method="post">
        <input name="name" type="text" required pattern="\w+" placeholder="Name"/>
        <input name="email" type="email" placeholder="Email"/>
        <input type="submit" value="Create"/>
    </form>

    <form action="getByEmail">
        <input name="email" type="email" placeholder="Email"/>
        <input type="submit" value="Find"/>
    </form>

    <form action="getByName">
        <input name="name" type="text" required pattern="\w+" placeholder="Name"/>
        <input name="pageNum" type="text" required pattern="\d+$" placeholder="Page Num"/>
        <input name="pageSize" type="text" required pattern="\d+$" placeholder="Page Size"/>
        <input type="submit" value="Find by name"/>
    </form>

    <a href="/mentee">back</a>
</@c.page>