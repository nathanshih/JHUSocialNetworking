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
            $.ajax({
                url: "register",
                contentType: "application/json",
                data: JSON.stringify({
                    "name": $("#name").val(),
                    "email": $("#email").val(),
                    "degreeProgram": $("#degree").val()
                }),
                dataType: "json",
                type: "POST",
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