/**
 * Created by Tianya on 2017/10/10.
 */

var datePattern = 'YYYY-MM-DD HH:mm:ss';

var ajaxSuccessText = '提交成功';
var ajaxErrorText = '系统开小差，请稍后尝试';

Array.prototype.distinct = function () {
    var x = [], r = [];
    for (var i = 0; i < this.length; i++) {
        x['_' + this[i]] = this[i];
    }
    for (var b in x) {
        if (typeof x[b] !== 'function') {
            r.push(x[b]);
        }
    }
    return r;
};

String.prototype.format = function (args) {
    var result = this;
    var reg;
    if (arguments.length > 0) {
        if (arguments.length === 1 && typeof (args) === 'object') {
            for (var key in args) {
                if (args.hasOwnProperty(key) && args[key] !== undefined) {
                    reg = new RegExp('({' + key + '})', 'g');
                    result = result.replace(reg, args[key]);
                }
            }
        }
        else {
            for (var i = 0; i < arguments.length; i++) {
                if (arguments[i] !== undefined) {
                    reg = new RegExp('({)' + i + '(})', 'g');
                    result = result.replace(reg, arguments[i]);
                }
            }
        }
    }
    return result;
};

function sendAjax(url, data, loading, method) {
    $.ajax({
        url: url,
        type: 'POST',
        timeout: 5000,
        data: data,
        dataType: 'json',
        beforeSend: function () {
            !!loading ? startLoading() : null;
        },
        complete: function () {
            !!loading ? stopLoading() : null;
        },
        success: function (data) {
            if (!!method) {
                eval(method);
            } else if (!!loading) {
                if (!!data['errorText']) {
                    alert(data['errorText']);
                } else if (!!data['success']) {
                    alert(ajaxSuccessText);
                    window.location.reload();
                } else {
                    alert(ajaxErrorText);
                }
            }
        },
        error: function () {
            if (!!loading) {
                alert(ajaxErrorText);
            }
        }
    });
}