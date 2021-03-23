import React from 'react';
import "./DataBox.css";

class DataBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            disabled : true,
            carInfo: "",
            form: {
                origin: props.origin,
                destination: props.destination,
                currentCharge: props.currentCharge
            }
        }
        this.onSubmitForm = this.onSubmitForm.bind(this);
        this.changeFormValue = this.changeFormValue.bind(this);
    }
    HandleChange()
    {
        this.setState({disabled: !this.state.disabled})
    }

    changeFormValue(e) {
        let {name, value} = e.target;
        this.setState({
            carInfo: value,

        });

    }

    onSubmitForm() {
        var carInfo = this.state.carInfo.split("/");
        var origin = this.state.form.carInfo;
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
            ROUTE_REQUEST.open("GET", "localhost:8787/api/stations/along/"+origin+"/"+outletType+"/"+destination+"/"+speedTable+"/"+currentCharge+"/"+maxCharge+"/"+chargingCurve+"/"+maxChargeAfterChargingStation, false);
            ROUTE_REQUEST.onload = function() {
                routeResponse = this.response;
                console.log(routeResponse);
                this.props.displayRoute(routeResponse);
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
                    <h2 type="">Trip Planner</h2>
                    <label htmlFor="">Starting point</label>
                    <input type="text" value={this.state.form.origin} name="startingPoint"
                           placeholder="Pollock Road, University Park, PA"/>


                    <label htmlFor="">Ending point</label>
                    <input type="checkbox" id="checkbox" onChange={this.HandleChange.bind(this)}/>
                    <input type="text" value={this.state.form.destination} name="endingPoint"
                           placeholder="20 W 34th St, New York, NY" disabled={(!this.state.disabled) ? "disabled": ""}/>

                    <h4>EV Specs</h4>
                    <label htmlFor="">Current State of Charge</label>
                    <input type="text" placeholder="80" value={this.state.form.currentCharge}/>

                    <label htmlFor=""> Make and Model </label>
                    <select className="box" onChange={this.changeFormValue}>
                        <option value="iec62196Type1Combo/110,0.165/60/0,50,9,52,12,54,15,54,18,54,21,54,24,55,27,55,30,55,33,37,36,37,39,37,42,23,45,23,48,23,51,16,54,16,57,10,60,4/60"> Chevy Bolt</option>
                        <option value="tesla,iec62196Type1Combo/110,0.15/79/0,50,7.9,100,15.8,120,23.7,120,31.6,120,39.5,120,47.4,90,55.3,80,63.2,55,71.1,30,79,5/79"> Tesla Model 3</option>
                    </select>

                    <button type="button" id = "submit">Submit</button>

                </form>
            </div>
        );

    }
}


export default DataBox;