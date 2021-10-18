
/*
        Name,   Address,                                 Address Type,  Tax Amount,     Biography
        Walt    410 Scott Drive Pittsburgh, PA 15237      Primary            3.0        Instructor at Tech Elevator
        Oliver  410 Scott Drive Pittsburgh, PA 15237      Secondary          3.0        Cat that interrupts class
        Taylor   55 House Road New York, New York 10001   Primary            4.0        Aspiring comedian


  Address Type Id               Address Type
  1                             Primary
  2                             Secondary

  Person_Id Address_Id         Address Type,  
   1           1                Primary                 
   2           1                Secondary            
   3           2                Primary                   
   2           3                Primary               
   
   Person_Id    Name    Biography
   1            Walt    Instructor at Tech Elevator
   2            Oliver  Cat that interrupts class
   3            Taylor  Aspiring comedian
   
   Address_Id   House #,        Street,         City Id         
   1            410             Scott Drive        1 
   2            55              House Road         2     
   3            410             Outside            1       
   
   State        Tax Amount
   PA           3.0
   NY           4.0
   
   City Id      Name            State   
   1            Pittsburgh      PA      
   2            New York        NY      
   
   
   
   Unique States and Tax amounts
*/