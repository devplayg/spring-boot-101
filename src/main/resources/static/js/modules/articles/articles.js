$(function() {
    // console.log(555);
    //console.log(queryParams());
    update();

    $('.btn-refresh').click(function(e) {
        // console.log('click');

        update();
    });

    // function queryParams(params) {
    //     params = filter;
    //     return params;
    // }

    function update() {
        // var url = '/app/articles/list?'+$.param(filter);
        // var url = '/app/articles/list?'+$.param(filter);
        console.log(filter);
        // console.log(filter);
        // console.log($.param(filter));
        // console.log(decodeURIComponent($.param(filter)));
        // console.log( Object.keys(filter).map(key => key + '=' + filter[key]).join('&') );
        //var url = '/app/articles/list?'+decodeURIComponent($.param(filter));
        var url = '/app/articles/list';
        console.log(url);

        //console.log(url);

        var param = {
            startDate: filter.startDate,
            endDate: filter.endDate,
            title: filter.title,
        };
        url += '?'+$.param(param);
        //
        // var paramArr = {
        //     org: filter.org,
        //     category: filter.category,
        //     riskLevel: filter.riskLevel,
        // };
        // // url += '?'+$.param(param)+'&'+decodeURIComponent($.param(paramArr));
        // url += '?'+$.param(filter);
        // console.log(url);
        // $("#table-article")

        // var $table = $("#table-article");
        // console.log( $table.data('url') );
        $("#table-article").bootstrapTable({
            url: url
        });

    }
});