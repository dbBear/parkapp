/*<![CDATA[*/
let latitudeDiv = $('#latitudeDiv');
let longitudeDiv = $('#longitudeDiv');
let latitude = $('#latitude');
let longitude = $('#longitude');
let gpsDiv = $('#gpsDiv');

function getLocation() {
  if (navigator.geolocation) {
    gpsDiv.removeAttr('hidden');
    navigator.geolocation.getCurrentPosition(showPosition);
  } else {
    gpsDiv.html(
        "<p>Geolocation is not supported/allowed.</p>"
    )
  }
}

function showPosition(position) {
  latitude.val(position.coords.latitude);
  longitude.val(position.coords.longitude);
}

/*]]>*/