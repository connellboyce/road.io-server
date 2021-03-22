import React from 'react';
import "./DataBox.css";

class DataBox extends React.Component {
    constructor(props) {
        super(props);
        this.state = {disabled : false}
    }
    HandleChange()
    {
        this.setState({disabled: !this.state.disabled})
    }


   render() {


        return (
            <div className="DataBox">
                <form>
                    <h2 type="">Home-Title</h2>
                    <label htmlFor="">Starting point</label>
                    <input type="text" name="startingPoint"
                           placeholder="Pollock Road, University Park, PA"/>


                    <label htmlFor="">Ending point</label>
                    <input type="checkbox" id="checkbox" onChange={this.HandleChange.bind(this)}/>
                    <input type="text" name="endingPoint"
                           placeholder="20 W 34th St, New York, NY" disabled={(!this.state.disabled) ? "disabled": ""}/>

                    <h4>EV Specs</h4>
                    <label htmlFor="">Current State of Charge</label>
                    <input type="text" placeholder="80%"/>
                    <label htmlFor="">Range (miles)</label>
                    <input type="text" placeholder="200"/>

                    <label htmlFor=""> Make and Model </label>
                    <select class="box">
                        <option value="Chevy Bolt"> Chevy Bolt</option>
                        <option value="Tesla Model 3"> Tesla Model 3</option>
                    </select>

                    <button type="submit" id = "submit">Submit</button>

                </form>
            </div>
        );

    }
}


export default DataBox;