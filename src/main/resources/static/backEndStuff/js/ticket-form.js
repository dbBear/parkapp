/*<![CDATA[*/
let latitudeDiv = $('#latitudeDiv');
let longitudeDiv = $('#longitudeDiv');
let latitude = $('#latitude');
let longitude = $('#longitude');
let gpsDiv = $('#gpsDiv');

function getLocation() {
  if (navigator.geolocation) {
    gpsDiv.removeAttr('hidden');
    // latitudeDiv.removeAttr('hidden');
    // longitudeDiv.removeAttr('hidden');
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
  // gpsDiv.append(
  //     "<label for='latitude'>Latitude</label>" +
  //     "<input id='latitude' type='text' class='form-control' value='" + position.coords.latitude +
  //         "'th:field='*{latitude}'>" +
  //     "<label for='Longitude'>Longitude</label>" +
  //     "<input id='Longitude' type='text' class='form-control' value='" + position.coords.longitude +
  //         "'th:field='*{longitude}'>"
  // )
}

$(document).ready(function() {
  // console.log("ready!")
  // latitudeDiv.hide();
  // longitudeDiv.hide();
});
/*]]>*/