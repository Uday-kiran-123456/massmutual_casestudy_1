import React, { useState } from "react";
import styles from './createForm.module.css';
import Header from './Header.jsx';
import Footer from './Footer.jsx';

function CreateForm() {
    const [formData, setFormData] = useState({
        name: '',
        dob: '',
        gender: '',
        email: '',
        phoneNumber: '',
        occupation: '',
        employerName: '',
        workAddress: '',
        insuranceType: 'Life insurance',
        coverageAmount: '',
        premiumPayment: '',
        additionalRiders: '',
        declaration: false
    });

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setFormData(prevFormData => ({
            ...prevFormData,
            [name]: type === 'checkbox' ? checked : value
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        
        if (!formData.declaration) {
            alert("You must accept the declaration to submit the form.");
            return;
        }

        fetch('http://localhost:8080/api/policies', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        })
        .then(response => response.json())
        .then(data => {
            console.log('Policy application submitted:', data);
            alert("Application submitted successfully!");
        })
        .catch(error => {
            console.error('Error submitting policy application:', error);
        });
    };

    return (
        <>
        <Header />
        <div className={styles.Formbg}>
            <h1 className={styles.title}>Policy Application Form</h1>
            <form onSubmit={handleSubmit}>
                <h4>Personal Information</h4>
                <label>Name</label><span style={{ color: 'red' }}>*</span>
                <input 
                    type="text" 
                    name="name" 
                    value={formData.name} 
                    onChange={handleChange} 
                    placeholder="Full Name" 
                /><br />

                <label>Date of Birth</label><span style={{ color: 'red' }}>*</span>
                <input 
                    type="date" 
                    name="dob" 
                    value={formData.dob} 
                    onChange={handleChange} 
                /><br />

                <label>Gender</label><span style={{ color: 'red' }}>*</span>
                <input 
                    type="radio" 
                    name="gender" 
                    value="Male" 
                    checked={formData.gender === 'Male'} 
                    onChange={handleChange} 
                /> Male
                <input 
                    type="radio" 
                    name="gender" 
                    value="Female" 
                    checked={formData.gender === 'Female'} 
                    onChange={handleChange} 
                /> Female
                <input 
                    type="radio" 
                    name="gender" 
                    value="Others" 
                    checked={formData.gender === 'Others'} 
                    onChange={handleChange} 
                /> Others
                <br />

                <label>E-mail</label><span style={{ color: 'red' }}>*</span>
                <input 
                    type="email" 
                    name="email" 
                    value={formData.email} 
                    onChange={handleChange} 
                    placeholder="example@example.com" 
                /><br />

                <label>Phone Number</label><span style={{ color: 'red' }}>*</span>
                <input 
                    type="text" 
                    name="phoneNumber" 
                    value={formData.phoneNumber} 
                    onChange={handleChange} 
                    placeholder="Phone number" 
                /><br />

                <h4>Employment Information</h4><br />
                <label>Occupation</label>
                <input 
                    type="text" 
                    name="occupation" 
                    value={formData.occupation} 
                    onChange={handleChange} 
                    placeholder="Occupation" 
                /><br />

                <label>Employer Name</label>
                <input 
                    type="text" 
                    name="employerName" 
                    value={formData.employerName} 
                    onChange={handleChange} 
                    placeholder="Employer Name" 
                /><br />

                <label>Work Address</label>
                <input 
                    type="text" 
                    name="workAddress" 
                    value={formData.workAddress} 
                    onChange={handleChange} 
                    placeholder="Work Address" 
                /><br />

                <h4>Insurance Coverage Details</h4>
                <label>Type of Insurance Needed</label>
                <select 
                    name="insuranceType" 
                    value={formData.insuranceType} 
                    onChange={handleChange}
                >
                    <option value="Life insurance">Life Insurance</option>
                    <option value="Auto insurance">Auto Insurance</option>
                    <option value="Travel insurance">Travel Insurance</option>
                    <option value="Health insurance">Health Insurance</option>
                    <option value="Home insurance">Home Insurance</option>
                    <option value="Business insurance">Business Insurance</option>
                </select><br />

                <label>Insurance Coverage Amount</label>
                <input 
                    type="text" 
                    name="coverageAmount" 
                    value={formData.coverageAmount} 
                    onChange={handleChange} 
                    placeholder="Coverage Amount" 
                /><br />

                <label>Premium Payment</label>
                <input 
                    type="radio" 
                    name="premiumPayment" 
                    value="Monthly" 
                    checked={formData.premiumPayment === 'Monthly'} 
                    onChange={handleChange} 
                /> Monthly
                <input 
                    type="radio" 
                    name="premiumPayment" 
                    value="Quarterly" 
                    checked={formData.premiumPayment === 'Quarterly'} 
                    onChange={handleChange} 
                /> Quarterly
                <input 
                    type="radio" 
                    name="premiumPayment" 
                    value="Annually" 
                    checked={formData.premiumPayment === 'Annually'} 
                    onChange={handleChange} 
                /> Annually
                <br />

                <label>Additional Riders (if any)</label>
                <input 
                    type="text" 
                    name="additionalRiders" 
                    value={formData.additionalRiders} 
                    onChange={handleChange} 
                    placeholder="Additional Riders" 
                /><br />

                <h4>Declaration</h4>
                <input 
                    type="checkbox" 
                    name="declaration" 
                    checked={formData.declaration} 
                    onChange={handleChange} 
                />
                <label>
                    I declare that the information in the application is true and complete to the best of my knowledge. I understand that any misrepresentation or omission may result in the denial of my insurance claim.
                </label><br />

                <input type="submit" value="Submit" id="submit" />
            </form>
        </div>
        <Footer />
        </>
    );
}

export default CreateForm;
