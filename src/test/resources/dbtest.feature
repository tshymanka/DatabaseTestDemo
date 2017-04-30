#Sample Feature Definition Template

Feature: No dups in Person Hub table
	
Scenario: Checking for no duplicates in Person Hub table
Given User is successfully connected to the database 
When User validates Person Hub for business keys duplicates 
Then 0 records returned from the database
	


