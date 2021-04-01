import React from 'react';
import "./DataBox.css";
import logo from "./globe-png-9acqaaMTM.png";

class DataBox extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            disabled : true,
            carInfo: "",
            form: {
                origin: {},
                destination: {},
                currentCharge: {}
            }
        }
        this.onSubmitForm = this.onSubmitForm.bind(this);
        this.changeFormValue = this.changeFormValue.bind(this);
        this.changeOrigin = this.changeOrigin.bind(this);
        this.changeDestination = this.changeDestination.bind(this);
        this.changeCharge = this.changeCharge.bind(this);
        this.toggleOriginSearch = this.toggleOriginSearch.bind(this);
        this.toggleDestinationSearch = this.toggleDestinationSearch.bind(this);
    }
    HandleChange()
    {
        this.setState({disabled: !this.state.disabled})
    }

    toggleOriginSearch() {
        let originFragment = this.state.form.origin;
        let originResponse = "";

        const ORIGIN_SEARCH_REQUEST = new XMLHttpRequest();
        ORIGIN_SEARCH_REQUEST.open("GET", "http://localhost:8787/api/autocomplete/"+originFragment+"/USA", false);
        ORIGIN_SEARCH_REQUEST.onload = function() {
            originResponse = JSON.parse(this.response);
            console.log(originResponse);
        }
        ORIGIN_SEARCH_REQUEST.send();
    }

    toggleDestinationSearch() {
        let destinationFragment = this.state.form.destination;
        let destinationResponse = "";

        const DESTINATION_SEARCH_REQUEST = new XMLHttpRequest();
        DESTINATION_SEARCH_REQUEST.open("GET", "http://localhost:8787/api/autocomplete/"+destinationFragment+"/USA", false);
        DESTINATION_SEARCH_REQUEST.onload = function() {
            destinationResponse = JSON.parse(this.response);
            console.log(destinationResponse);
        }
        DESTINATION_SEARCH_REQUEST.send();
    }

    changeFormValue(e) {
        let {name, value} = e.target;
        this.setState({
            carInfo: value,

        });

    }

    changeOrigin(e) {
        let {name, value} = e.target;
        this.setState({
            form: {
                origin: value,
                destination: this.state.form.destination,
                currentCharge: this.state.form.currentCharge
            }
        });
    }

    changeDestination(e) {
        let {name, value} = e.target;
        this.setState({
            form: {
                origin: this.state.form.origin,
                destination: value,
                currentCharge: this.state.form.currentCharge
            }
        });
    }

    changeCharge(e) {
        let {name, value} = e.target;
        this.setState({
            form: {
                origin: this.state.form.origin,
                destination: this.state.form.destination,
                currentCharge: value
            }
        });
    }

    onSubmitForm() {
        let lclProps = this.props;
        var carInfo = this.state.carInfo.split("/");
        var origin = this.state.form.origin;
        var destination = this.state.form.destination;
        var currentCharge = this.state.form.currentCharge;
        var outletType = carInfo[0];
        var speedTable = carInfo[1];
        var maxCharge = carInfo[2];
        var chargingCurve = carInfo[3];
        var maxChargeAfterChargingStation = carInfo[4];
        var routeResponse;
        if (this.state.disabled === false) {
            this.props.getStations();
        } else if (this.state.disabled === true) {
            const ROUTE_REQUEST = new XMLHttpRequest();
            ROUTE_REQUEST.open("GET", "http://localhost:8787/api/stations/along/"+origin+"/"+outletType+"/"+destination+"/"+speedTable+"/"+currentCharge+"/"+maxCharge+"/"+chargingCurve+"/"+maxChargeAfterChargingStation, true);
            ROUTE_REQUEST.onload = function() {
                routeResponse = JSON.parse(this.response);
                console.log(routeResponse)
                //BROKEN
                lclProps.displayRoute(routeResponse);
            }
            ROUTE_REQUEST.send();
        } else {
            console.log("State error for variable: disabled");
        }

    }


   render() {

        return (
            <div className="DataBox">
                <form>
                    <div id="logo">
                        <h2 type="">road.i<img src={logo} className="DataBox-logo" alt="logo"/></h2>

                    </div>



                    <label htmlFor="">Starting point</label>
                    <input type="text" onChange={this.changeOrigin} name="startingPoint"
                           placeholder="Pollock Road, University Park, PA"/>
                    <button type="button" class="search" onClick={this.toggleOriginSearch}>Search</button>
                    <br/>

                    <label htmlFor="">Ending point</label>
                    <input type="checkbox" id="checkbox" onChange={this.HandleChange.bind(this)}/>
                    <input type="text" onChange={this.changeDestination} name="endingPoint"
                           placeholder="Tampa, FL" disabled={(!this.state.disabled)}/>
                    <button type="button" class="search" onClick={this.toggleDestinationSearch}>Search</button>
                    <br/>

                    <h4>EV Specs</h4>
                    <label htmlFor="">Current State of Charge</label>
                    <input type="text" name="currentCharge" placeholder="80" onChange={this.changeCharge}/>

                    <label htmlFor=""> Make and Model </label>
                    <select className="box" onChange={this.changeFormValue}>
                        <option value=""></option>
                        <option value="iec62196Type1Combo/110,0.165/60/0,50,9,52,12,54,15,54,18,54,21,54,24,55,27,55,30,55,33,37,36,37,39,37,42,23,45,23,48,23,51,16,54,16,57,10,60,4/60"> Chevy Bolt</option>
                        <option value="tesla,iec62196Type1Combo/110,0.15/79/0,50,7.9,100,15.8,120,23.7,120,31.6,120,39.5,120,47.4,90,55.3,80,63.2,55,71.1,30,79,5/79"> Tesla Model 3</option>
                    </select>

                    <button type="button" id = "submit" onClick={this.onSubmitForm}>Submit</button>

                </form>
            </div>
        );

    }
}


export default DataBox;