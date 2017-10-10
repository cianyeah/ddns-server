/**
 * Created by Tianya on 2017/10/10.
 */

var body = $('body');
var mainTable = body.find('#mainTable');

var addRecordForm = $('#addRecordForm');

$(document).ready(function () {
    getDnsRecord();
});

function getDnsRecord() {
    sendAjax('dns/getDnsARecord', null, true, 'fillDnsRecord(data)');
}

function fillDnsRecord(data) {
    if (!!data['errorText']) {
        alert(data['errorText']);
        return;
    }
    if (!data['success']) {
        alert(ajaxErrorText);
        return;
    }

    var tbody = mainTable.find('tbody');
    var dnsARecordSet = data['successInfo'];
    $.each(dnsARecordSet, function (index, dnsARecord) {
        tbody.append($('<tr>')
            .append($('<td>').text('A'))
            .append($('<td>').text(dnsARecord['domain']))
            .append($('<td>').text(dnsARecord['ip']))
            .append($('<td>')));
    });
}

function addDnsRecord() {
    var ajaxData = addRecordForm.serializeArray();
    var now = moment().format(datePattern);
    ajaxData.push({'name': 'token', 'value': now});
    sendAjax('dns/addDnsARecord', ajaxData, true);
}