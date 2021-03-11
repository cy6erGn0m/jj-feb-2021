function loadPart(partId) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (request.readyState == 4) {
            var part = JSON.parse(request.responseText);

            var partInfoElement = document.getElementById('partInfo');

            partInfoElement.textContent = "partNumber: " + part.partNumber +
                ", title: " + part.title;
        }
    };
    request.open("get", "/api/part/" + partId, true);
    request.send();
}
