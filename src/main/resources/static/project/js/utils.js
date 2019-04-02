//礼物机类型
function showTGiftModelSelect(selectId,defaultValue){
    $.ajax({
        url :'/web/tGiftModel/getTGiftModel',
	type:"post",
    data : {
    },
    success : function(result) {
        if(result.status == 0){
            $("#"+selectId).html('<option value="">全部</option>');
            $.each(result.result, function (i, detail) {
                var id = detail['id'];
                if(defaultValue != undefined && defaultValue != null && id == defaultValue){
                    $("#"+selectId).append('<option selected="selected" value="'+id+'">'+id+'</option>');
                }else{
                    $("#"+selectId).append('<option value="'+id+'">'+id+'</option>');
                    }
                });
            }else{
                alert(result.message);
            }
        }
    });
}
//礼物机位置
function showAddressSelect(code,selectId,defaultValue){
    $.ajax({
        url :ctx +'gift/commonInterface/getAddress',
	type:"get",
    data : {
        code:code
    },
    success : function(result) {
        if(result.status == 0){
            $("#"+selectId).html('<option value="">全部</option>');
            $.each(result.result, function (i, detail) {
                var code = detail['code'];
                var name = detail['shortName'];
                var lng = detail['lng'];
                var lat = detail['lat'];
                if(defaultValue != undefined && defaultValue != null && code == defaultValue){
                    $("#"+selectId).append('<option selected="selected" value="'+code+'">'+name+'</option>');
                }else{
                    $("#"+selectId).append('<option value="'+code+'">'+name+'</option>');
                    }
                });
            }else{
            	$.modal.alertError(result.message);
            }
        }
    });
}

//商品类型
function showTGoodsTypes(selectId,defaultValue){
	$.ajax({
    url :'/web/tGoodsType/getTGoodsTypes',
    type:"post",
    data : {
    },
    success : function(result) {
        if(result.status == 0){
            $("#"+selectId).html('<option value="">全部</option>');
            $.each(result.result, function (i, detail) {
                var code = detail['id'];
                var name = detail['name'];
                if(defaultValue != undefined && defaultValue != null && code == defaultValue){
                    $("#"+selectId).append('<option selected="selected" value="'+code+'">'+name+'</option>');
                }else{
                    $("#"+selectId).append('<option value="'+code+'">'+name+'</option>');
                    }
                });
            }else{
                $.modal.alertError(result.message);
            }
        }
    });
}

