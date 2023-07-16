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
    <div class="row" id="list">
        <#list list as item>
            <div class="col-md-1 col-sm-3 col-3 item">
                <div class="card">
                    <a href="${item.pageUrl}" target="_blank">
                        <img src="${item.imgUrl}" class="card-img-top">
                        <div class="card-body">
                            ${item.title} <br>
                            ${item.tags?if_exists} <br>
                            ${item.id}
                        </div>
                    </a>
                </div>
            </div>
        </#list>
    </div>
</div>

<script src="${request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script src="${request.contextPath}/js/bootstrap.bundle.min.js"></script>

<style>
    .item {
        text-align: center;
        margin-bottom: 5px;
    }
    .col-3{
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
    .col-sm-3{
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
    .col-md-1{
        padding-left: 0 !important;
        padding-right: 0 !important;
    }
</style>

<script>
    $(document).ready(function () {
        adjustItems();
        $(window).resize(function () {
            adjustItems();
        });
    });

    function adjustItems() {
        console.log('adjustItems')
        console.log('$(window).width(): ' + $(window).width())
        if ($(window).width() < 576) {
            $('#list .item').removeClass('col-md-1').removeClass("col-sm-3").addClass('col-3');
        } else if ($(window).width() < 768) {
            $('#list .item').removeClass('col-md-1').removeClass("col-3").addClass('col-sm-3');
        } else {
            $('#list .item').removeClass('col-sm-3').removeClass("col-3").addClass('col-md-1');
        }
    }

</script>

</body>
</html>