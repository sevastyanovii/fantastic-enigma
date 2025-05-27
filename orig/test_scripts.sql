declare
  v_amt number;
begin

  dbms_output.put_line('*** TEST1');
  ClassB.do_calc_interest(p_amount => 2000, p_interest => v_amt, p_comm_amt => 100);
  dbms_output.put_line('interest = '||v_amt);

  dbms_output.put_line('');

  dbms_output.put_line('*** TEST2');
  ClassA.set_default_rate(0.5);
  v_amt := ClassA.calc_default_interest(1000);
  dbms_output.put_line('interest = '||v_amt);

  dbms_output.put_line('');

  dbms_output.put_line('*** TEST3');
  ClassA.set_default_rate(0.6);
  v_amt := ClassA.calc_special_interest(1000);
  dbms_output.put_line('interest = '||v_amt);

  dbms_output.put_line('');

  dbms_output.put_line('*** TEST4');
  v_amt := ClassA.calc_special_interest(1000, 0.3);
  dbms_output.put_line('interest = '||v_amt);

  dbms_output.put_line('');

  dbms_output.put_line('*** TEST5');
  v_amt := ClassA.calc_special_interest(1000, null);
  dbms_output.put_line('interest = '||v_amt);

end;
/
