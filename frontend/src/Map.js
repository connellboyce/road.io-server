import React, {Component} from 'react';

class Map extends Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false
        };
    }

    componentDidMount() {
        this.setState({
            isLoaded: true
        });
    }

    render() {
        const { error, isLoaded } = this.state;
            return (
                <div>
                    <h1>I'm a map!</h1>
                </div>
            );
    }


}

export default Map;