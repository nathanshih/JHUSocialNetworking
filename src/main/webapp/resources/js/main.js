// used from http://www.w3schools.com/js/js_cookies.asp
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}

$(document).ready(function() {

    // Activated when clicking "Home" from left navigation panel
    $("#home").click(function() {
        // clear contentLeft & contentRight
        $("#contentLeft").empty();
        $("#contentRight").empty();
        
        // slide out profileForm if it is present
        $("#profileForm").slideUp("slow", function() {
            $("#contentLeft").slideDown("slow");
            $("#contentRight").slideDown("slow");
        });
    });

    // Activated when clicking "Profile" from navigation panel
    $("#profile").click(function() {
        // slide out any forms if present
        $("#contentLeft").slideUp("slow", function() {
            $("#profileForm").slideDown("slow");
        });
        $("#contentRight").slideUp("slow", function() {});
    });

    // Activated when clicking the "Update" button on the Profile form
    $("#updateProfile").click(function() {
        // first check that the email is in correct format and all fields are filled in
        var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
        if ($("#name").val() === '' || $("#email").val() === '' 
          || $("#degree").val() === '') {
            alert("All fields required.");
        } else if (!($("#email").val()).match(email)) {
            alert("Invalid email entered.");
        } else {
            // get user student ID from session cookie
            var studentId = getCookie("studentId");
            $.ajax({
                url: "updateStudent",
                contentType: "application/json",
                data: JSON.stringify({
                    "id": studentId,
                    "name": $("#name").val(),
                    "email": $("#email").val(),
                    "discipline": $("#degree").val()
                }),
                dataType: "json",
                type: "PUT",
                success: function() {
                    alert("Profile successfully updated.");
                },
                error: function() {
                    alert("Unable to update profile information.");
                }
            });

            // slide out profileForm
            $("#profileForm").slideUp("slow", function() {
                $("#contentLeft").slideDown("slow");
                $("#contentRight").slideDown("slow");
            });
        }
    });
});