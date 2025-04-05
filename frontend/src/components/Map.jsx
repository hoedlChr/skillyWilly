import React from "react";
import { MapContainer, Marker, Popup, TileLayer, useMap } from "react-leaflet";


export default function Map({markers, center=[51.505, -0.09], zoom=13}) {

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
    <MapContainer center={center} zoom={zoom} scrollWheelZoom={false}>
      <TileLayer
        attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      {markersList}
      <Marker position={[51.505, -0.09]}>
        <Popup>
          This is a popup
        </Popup>
      </Marker>
    </MapContainer>
  );
}