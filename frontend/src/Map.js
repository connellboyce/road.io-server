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
            apikey: "{HERE-API-KEY}"
        });

        const defaultLayers = platform.createDefaultLayers();

        // Create an instance of the map
        const map = new H.Map(
            this.mapRef.current,
            defaultLayers.vector.normal.map,
            {
                // This map is centered over Europe
                center: { lat: 50, lng: 5 },
                zoom: 4,
                pixelRatio: window.devicePixelRatio || 1
            }
        );

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