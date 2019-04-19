$(function() {
    // console.log(555);
    update();

    $('.btn-refresh').click(function(e) {
        // console.log('click');

        update();
    });

    function update() {
        var url = '/app/articles/list?'+$.param(filter);
        console.log(url);
        console.log(filter);
        // console.log(filter);
        // console.log($.param(filter));
        // $("#table-article")
        // var $table = $("#table-article");
        // console.log( $table.data('url') );
        $("#table-article").bootstrapTable({
            url: url
        });

    }
});