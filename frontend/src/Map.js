import React, {Component} from 'react';
import DataBox from './DataBox';
class Map extends Component {
    mapRef = React.createRef();

    state = {
        // The map instance to use during cleanup
        map: null
    };

    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false
        };
    }

    componentDidMount() {
        const H = window.H;
        const platform = new H.service.Platform({
            apikey: "OUR-API-KEY"
        });

        const defaultLayers = platform.createDefaultLayers();


        // Create an instance of the map
        const map = new H.Map(
            this.mapRef.current,
            defaultLayers.vector.normal.map,
            //For satellite map:
            //defaultLayers.raster.satellite.map,
            {
                // Center map over eastern U.S.
                center: { lat: 40, lng: -77 },
                zoom: 4,
                pixelRatio: window.devicePixelRatio || 1
            }
        );
        //Add traffic layer (shows areas of high congestion in yellow/red)
        //map.addLayer(defaultLayers.vector.normal.traffic);

        // Lines 45-93 do some basic routing:
        const routingParameters = {
            'routingMode': 'fast',
            'transportMode': 'car',
            // The start point of the route:
            'origin': '40.790741,-77.858485',
            // The end point of the route:
            'destination': '27.981661,-82.408064',
            // Include the route shape in the response
            'return': 'polyline'
        };

        // Define a callback function to process the routing response:
        var onResult = function(result) {
            // ensure that at least one route was found
            if (result.routes.length) {
                result.routes[0].sections.forEach((section) => {
                    // Create a linestring to use as a point source for the route line
                    let linestring = H.geo.LineString.fromFlexiblePolyline(section.polyline);

                    // Create a polyline to display the route:
                    let routeLine = new H.map.Polyline(linestring, {
                        style: { strokeColor: 'blue', lineWidth: 3 }
                    });

                    // Create a marker for the start point:
                    let startMarker = new H.map.Marker(section.departure.place.location);

                    // Create a marker for the end point:
                    let endMarker = new H.map.Marker(section.arrival.place.location);

                    // Add the route polyline and the two markers to the map:
                    map.addObjects([routeLine, startMarker, endMarker]);

                    // Set the map's viewport to make the whole route visible:
                    map.getViewModel().setLookAtData({bounds: routeLine.getBoundingBox()});
                });
            }
        };

        // Get an instance of the routing service version 8:
        var router = platform.getRoutingService(null, 8);

        // Call calculateRoute() with the routing parameters,
        // the callback and an error callback function (called if a
        // communication error occurs):
        router.calculateRoute(routingParameters, onResult,
            function(error) {
                alert(error.message);
            });

        // MapEvents enables the event system
        // Behavior implements default interactions for pan/zoom (also on mobile touch environments)
        // This variable is unused and is present for explanatory purposes
        const behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

        // Create the default UI components to allow the user to interact with them
        // This variable is unused
        const ui = H.ui.UI.createDefault(map, defaultLayers);

        this.setState({ map });
    }

    componentWillUnmount() {
        // Cleanup after the map to avoid memory leaks when this component exits the page
        this.state.map.dispose();
    }

    render() {
        const { error, isLoaded } = this.state;
            return (
                <div ref={this.mapRef} style={{ height: "900px" }}>
                <DataBox />
                </div>

            );
    }


}

export default Map;