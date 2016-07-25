<#-- @ftlvariable name="client" type="coffee.synyx.auth.web.ClientDetailsResource" -->
<#import "/spring.ftl" as spring/>

<!DOCTYPE html>
<html lang="en" xmlns:sec="http://www.springframework.org/security/tags" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimal-ui, user-scalable=no"/>
    <title><@spring.message "clients.all.title"/></title>

    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/auth.css"/>
</head>
<body>
<div class="row">
    <div class="col-md-offset-2 col-md-8 col-xs-12">
        <table class="table table-striped">
            <tr>
                <th>
                    <@spring.message "client.id.header"/>:
                </th>
                <td>${client.clientId}</td>
            </tr>
            <tr>
                <th>
                <@spring.message "client.scope.header"/>:
                </th>
                <td>${client.scope}</td>
            </tr>
            <tr>
                <th>
                <@spring.message "client.redirect.header"/>:
                </th>
                <td>${client.registeredRedirectUri}</td>
            </tr>
        </table>
    </div>
</div>
<script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>