<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">

    <title>Title</title>

    <link href="${request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${request.contextPath}/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="${request.contextPath}/css/bootstrap-reboot.min.css">

</head>
<body class="bg-light">

<form action="/" method="get">
    <input type="text" name="keyword" class="form-control">
    <button style="margin: 1rem" class="btn btn-success" type="submit">Submit</button>
</form>

结果： ${list?size}

<div class="container-fluid">
    <div class="row">
        <#list list as item>
            <div class="card" style="display: flex; width: 33.3%">
                <a href="${item.pageUrl}" target="_blank">
                    <img src="${item.imgUrl}" class="card-img-top">
                    <div class="card-body">
                        ${item.title}
                    </div>
                </a>
            </div>
        </#list>
    </div>

</div>

<script src="${request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${request.contextPath}/js/bootstrap.bundle.min.js"></script>

</body>
</html>