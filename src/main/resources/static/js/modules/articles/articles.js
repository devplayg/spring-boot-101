$(function() {
    // console.log(555);
    update();

    $('.btn-refresh').click(function(e) {
        // console.log('click');

        update();
    });

    function update() {
        console.log(filter);
        console.log($.param(filter));
        // $("#table-article")
        // var $table = $("#table-article");
        // console.log( $table.data('url') );
        $("#table-article").bootstrapTable({
            url: '/app/articles/list?fastPaging=false'
        });

    }
});