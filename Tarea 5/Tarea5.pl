% T A R E A  - 5 - P R O L O G %

%Suma y Resta de variables  el codigo.
suma(A,B,C):-C is A+B.
resta(A,B,C):-C is A-B.



%SEPPARIMPAR% (16 pts)

%Si las tres listas estan vacias finaliza la recursion.
sepparimpar([],[],[]).

%Para casos de listas de posiciones impares.
sepparimpar([P],[P],[]).

% Se los primeros 2 elementos de la lista L, que se comparan con la cabeza de P e I. Y se llama recursivamente con la cola de L, P e I.
sepparimpar([P,I|L],[P|A],[I|M]):- sepparimpar(L,A,M). 



%TODOSRANGO% (27 pts)

%Revisa si la lista L1 es sublista de la lista L2
pertenece([],L,L).
pertenece([X|L1],L2,[X|L3]):-pertenece(L1,L2,L3).

%Crea una lista con todos los valores entre X e Y.
range(X,X,[X]).
range(X,Y,[X|L]) :- X < Y, X1 is X + 1,
    range(X1,Y,L).

%Muestra los n! casos de la Lista cuando esta es acotada con length.
permutacion([],[]).
permutacion(L1,[X|L2]):-select(X,L1,L3),
    permutacion(L3,L2).

%Mientras X pertenezca a la lista (osea L no es [_,(...),_]), revisa si el rango entre X e Y esta en la Lista.
todosrango(L,X,Y):-member(X,L),
    sort(L,SortList),
    resta(Y,1,Y1),
    range(X,Y1,LRange),
    pertenece(_,LRange,SortList).
    
%Si el anterior dio falso, es porque la lista es [_,(...),_]), por lo tanto se crean los n! casos entre el rango[X-Y]
todosrango(L,X,Y):-resta(Y,1,Y1),
    range(X,Y1,LRange),
    append(LRange, [_], NewRange),
    permutacion(NewRange,L).
