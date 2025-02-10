# **TripsPlanner**

**TripsPlanner** is an application designed to help users plan and manage their future trips.

## **Features**
TripsPlanner allows users to:
- Register and log in to their account
- Plan, create, update, and delete trips
- Add and edit notes for each trip
- Search for specific terms in trips
- Filter upcoming trips happening within the next **30 days** or **this year**
- Manage account settings (update username, email, password)
- Navigate between different screens with a user-friendly UI

### **Tech Stack**
- **SQL Database** for data storage
- **Java with JavaFX** for UI
- **MVC Pattern** for better structure

## **User Interface**
- **User-friendly screens** for easy navigation
- **Interactive alerts** to enhance user experience

## **For Developers**

### **Key Functionalities**
- **Authentication:** Register and log in
- **Trip Management:** Create, update, delete trips
- **Notes:** Add/edit notes for trips
- **Search & Filters:** Search for keywords, filter upcoming trips
- **Account Management:** Change username, email, password

### **Application Structure**
TripsPlanner consists of **four main screens**, **two modal screens**, and **one startup screen**:

- **Main Screens:**
  1. **Login Screen**
  2. **Sign-Up Screen**
  3. **Main Screen**
  4. **Account Settings Screen**

- **Modal Screens:**
  - **Trip Notepad**
  - **Alerts**

## **Getting Started**

### **Prerequisites**
To run this project, you'll need:
- **Java** (version >= 8)
- **Maven** for dependency management
- **SQL Database** setup 

### **Installation**

1. **Clone the repository:**
    ```bash
   git clone https://github.com/yourusername/TripsPlanner.git

2. **Navigate to the project directory:**
    ```bash
    cd TripsPlanner

3. **Install dependencies:**
    ```bash
    mvn install

4. **Set up the database (See Database Entity Relationship Diagram (ERD)):**


5. **Run the application:**
    ```bash
    mvn javafx:run

## **Contributing**

1. Fork the repository
2. Create a new branch (git checkout -b feature/your-feature-name)
3. Make your changes and commit them (git commit -am 'Add new feature')
4. Push to your branch (git push origin feature/your-feature-name)
5. Create a new Pull Request

## **License**
* Distributed under the MIT License. See LICENSE for more information.
