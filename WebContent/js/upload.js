var is_pro=0;
var proid=0;
function fileSelected() {
    var file = document.getElementById('fileToUpload').files[0];
    if (file) {
        var fileSize = 0;
        if (file.size > 1024 * 1024)
            fileSize = (Math.round(file.size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
        else
            fileSize = (Math.round(file.size * 100 / 1024) / 100).toString() + 'KB';
        document.getElementById('fileName').innerHTML = 'Name: ' + file.name;
        document.getElementById('fileSize').innerHTML = 'Size: ' + fileSize;
        document.getElementById('fileType').innerHTML = 'Type: ' + file.type;
        if(is_pro==1)
        {
            $('#modal-btn-a').prop("disabled",false);
            $('#modal-btn-b').prop("disabled",false);
            $('#modal-btn-c').prop("disabled",false);
        }
    }
}

function uploadFile(type) {
    var fd = new FormData();
    fd.append("fileToUpload", document.getElementById('fileToUpload').files[0]);
    if(type!="notype")
    fd.append("type", type);
    if(is_pro==1)
    fd.append("pid", proid);
    var xhr = new XMLHttpRequest();
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);
    xhr.open("POST", "manager.do/file/upload"); 
    xhr.send(fd);
}

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
    } else {
        document.getElementById('progressNumber').innerHTML = 'unable to compute';
    }
}

function uploadComplete(evt) {
    /* 服务器端返回响应时候触发event事件*/
    toastr.success("upload success!");
    if(is_pro==0)
    loadpage();
    else{
        reload(proid);
    }
}

function uploadFailed(evt) {
    toastr.error("There was an error attempting to upload the file.");
}

function uploadCanceled(evt) {
    toastr.error("The upload has been canceled by the user or the browser dropped the connection.");
}
function nowpro()
{
    is_pro=1;
}
function setproid(id)
{
    proid=id;
}