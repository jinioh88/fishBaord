var replyManager = (function () {
   var getAll = function (obj,callback) {
        console.log("get All...");
        $.getJSON('/replies/'+obj,callback);
   };

    var update = function (obj,callback) {
        console.log("update...");
    };

    var remove = function (obj,callback) {
        console.log("remove...");
    };

    var add = function (obj, callback) {
        console.log("add...");

        $.ajax({
            type: 'post',
            url: '/replies/'+obj.bno,
            data: JSON.stringify(obj),
            dataType: 'json',
            contentType: "application/json",
            success: callback
        });
    }

    return {
        getAll: getAll,
        add: add,
        update: update,
        remove: remove
    }
})();