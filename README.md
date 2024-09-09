# District-and-Location-Based-Martyrs-Records-Management-System-in-Java
This Java project is designed to implement a data structure for managing martyrs’ records using sorted doubly and singly linked lists. The purpose of this project is to organize and manage data about martyrs, categorized by district, location, and individual details like name, age, and gender. The project involves building a doubly linked list for districts, where each node represents a district and contains a reference to a singly linked list of locations. In turn, each location in this singly linked list contains a reference to another singly linked list of martyrs sorted by age. The user can interact with the system through a graphical user interface (GUI) created using JavaFX, updating, deleting records, navigating through the lists, and generating statistics such as total number of martyrs, gender breakdown, average age, and more.

This project exemplifies key Object-Oriented Programming (OOP) concepts. Encapsulation is demonstrated as each record (district, location, martyr) is managed through linked lists with specific methods for adding, updating, and deleting, protecting the data and only exposing necessary operations. Inheritance and polymorphism are not explicitly stated, but could be applied in enhancing the project, for example, by creating a general list class from which both doubly and singly linked lists inherit. Finally, abstraction is present in the GUI layer, which allows users to interact with the data without knowing how it is structured or implemented behind the scenes. This highlights how Java's OOP principles are effectively used to manage complexity in data management systems​.
