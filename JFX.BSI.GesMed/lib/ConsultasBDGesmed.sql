SELECT * FROM Conta Where month(DataLancamento)=5 AND year(DataLancamento)=2018;

SELECT * FROM Conta Where month(DataPagamento)=5 AND year(DataPagamento)=2018 order by DataPagamento;

SELECT * FROM Conta Where month(DataLancamento)=5 AND year(DataLancamento)=2018 AND DataPagamento=NULL order by DataLancamento;

SELECT * FROM Conta Where month(DataPagamento)=5 AND year(DataPagamento)=2018 AND day(DataPagamento)=30 AND Tipo='Entrada';



SELECT SUM(Valor) FROM Conta Where month(DataPagamento)=5 AND year(DataPagamento)=2018 order by DataPagamento;