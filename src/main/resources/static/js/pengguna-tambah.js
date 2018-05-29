$(document).ready(function() {
    $(".hapus").hide();
    $('#EmployeeTable').DataTable();
    $('.uname').select2({
        placeholder: 'Pilih username SSO terdaftar'
    });
    $('.employee').select2({
        placeholder: 'Pilih nama employee terdaftar'
    });
    $("#modal-hapus").click(function() {
        $(".hapus").removeClass("is-active");
    });

    $("#modal-hapus-cancel").click(function() {
        console.log('masuk cuyyyy')
        $(".hapus").hide();
    });
} );

function showModalHapus(id) {
    $(".hapus").show();
    $("#confirmNonaktif").attr("href", "/employee/delete/" + id);
};

function cancelModalHapus() {
    $(".hapus").hide();
    $("#confirmNonaktif").attr("href", "#");
};

function cancelModalNotif() {
    $(".notif").hide();
};

function saveForm() {
    $('#formTambahPengguna').submit();
};

function openConfirmationModal() {
    $(".modal").addClass("is-active");
};

function closeConfirmationModal() {
    $(".modal").removeClass("is-active");
};