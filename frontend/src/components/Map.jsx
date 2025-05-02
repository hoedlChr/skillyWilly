import { Icon } from "leaflet";
import React from "react";
import { MapContainer, Marker, Popup, TileLayer, useMap } from "react-leaflet";


export default function Map({markers, center=[51.505, -0.09], zoom=13, style}) {

    let markersList = markers.map((marker, index) => {
        return (
            <Marker key={index} position={marker.position}>
                <Popup>
                    {marker.content}
                </Popup>
            </Marker>
        )
    });
  return (
    <MapContainer center={center} zoom={zoom} scrollWheelZoom={false} style={style}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      <Marker key={"myPosition"} position={center} icon={
        new Icon ({
          iconUrl: 'https://img.icons8.com/plasticine/100/exterior.png',
          iconSize: [38, 45], // size of the icon
          iconAnchor: [22, 94], // point of the icon which will correspond to marker's location
          popupAnchor: [-3, -76] // point from which the popup should open relative to the iconAnchor
        })
      }>
        <Popup>
          your position
        </Popup>
      </Marker>
      {markersList}
    </MapContainer>
  );
}