import React, {Component} from 'react';
import logo from './globe-png-9acqaaMTM.png';
import './App.css';
import Map from './Map.js';

class App extends Component {
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
                <div className="App">
                    <header className="App-header">
                        <img src={logo} className="App-logo" alt="logo"/>
                        <p>
                            Click here to enter
                        </p>
                        <a href="./App.css" className="button1">Button</a>
                        <a
                            className="App-link"
                            href="https://reactjs.org"
                            target="_blank"
                            rel="noopener noreferrer"
                        >
                            Learn React!
                        </a>
                        <Map />
                    </header>
                </div>
            );

    }


}

export default App;
