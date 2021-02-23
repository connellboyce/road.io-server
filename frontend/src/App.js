import React, {Component} from 'react';
import logo from './globe-png-9acqaaMTM.png';
import './App.css';
import Map from './Map.js';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            showMap: false
        };
    }

    componentDidMount() {
        this.setState({
            isLoaded: true
        });
    }

    showTheMap() {
        this.setState(state => ({
            showMap: true
        }));
        console.log("setting state to true");
    }

    render() {
        const { error, isLoaded, showMap } = this.state;
        if (error == null) {
            if (showMap) {
                return (
                    <div className="App">
                        <header className="App-header">
                            <Map/>
                        </header>
                    </div>
                );
            } else {
                return (
                    <div className="App">
                        <header className="App-header">
                            <img src={logo} className="App-logo" alt="logo"/>
                            <p>
                                Click here to enter
                            </p>
                            <a className="button1" onClick={this.showTheMap.bind(this)}>Button</a>
                            <a
                                className="App-link"
                                href="https://reactjs.org"
                                target="_blank"
                                rel="noopener noreferrer"
                            >
                                Learn React!
                            </a>
                        </header>
                    </div>
                );
            }
        }
    }
}

export default App;
