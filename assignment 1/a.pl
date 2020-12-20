man(marcus).
pompeian(marcus).
born(marcus,40).
mortal(X):-man(X).
die(X,79):-pompeian(X).
volcano(79).
dead(X,Z):-
	mortal(X),born(X,Y),(Z-Y>150);
	die(X,Y),(Z>=Y);
	not(alive(X,Z)).
alive(X,Y):-not(dead(X,Y)).



    
