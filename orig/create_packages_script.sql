create or replace package ClassA
is

  -- установить значение процентной ставки по умолчанию, для дальнейших расчетов
  procedure set_default_rate(p_rate number);

  -- получить текущее значение процентной ставки по умолчанию
  function get_default_rate
  return number;

  -- рассчитать сумму процентов с использование ставки по умочанию
  function calc_default_interest(p_amount number)
  return number;

  -- рассчитать сумму процентов с заданной ставкой
  -- если ставка не будет задана, будет использована ставка по умолчанию
  -- если принудительно указать null ставку будет использованная специальная ставка
  function calc_special_interest(p_amount number, p_rate in number default get_default_rate)
  return number;

end ClassA;
/

create or replace package ClassB
is

  gc_calculator_version varchar2(100) := 'calc v1.0';

  -- расчет суммы процентов по заданной ставке
  -- если ставка не задана будет использована ставка по умолчани
  procedure do_calc_interest
  (
    p_amount   number
   ,p_interest out number
   ,p_tariff   varchar2 default 'DEF'
   ,p_rate     number default ClassA.get_default_rate
   ,p_comm_amt number default null
  );

end ClassB;
/

create or replace package body ClassA
is
  gv_default_rate number;

  function get_special_rate
  return number
  is
  begin
     dbms_output.put_line('special rate requested');
     return 0.2;
  end;

  procedure set_default_rate(p_rate number)
  is
  begin
    dbms_output.put_line('change default rate from '||gv_default_rate||' to '||p_rate);
    gv_default_rate := p_rate;
  end;

  function calc_default_interest(p_amount number)
  return number
  is
    v_amt  number := 1000;
  begin
    --
    ClassB.do_calc_interest(p_amount, v_amt, 'TARIFF1', p_comm_amt => 10);
    --
    return v_amt;
    --
  end calc_default_interest;


  function calc_special_interest(p_amount number, p_rate in number default get_default_rate)
  return number
  is
    v_amt  number := 1000;
    v_rate number := coalesce(p_rate, get_special_rate);
  begin
    --
    ClassB.do_calc_interest(p_amount, v_amt, p_rate => v_rate);
    --
    return v_amt;
    --
  end calc_special_interest;


  function get_default_rate
  return number
  is
  begin
    return gv_default_rate;
  end get_default_rate;

  procedure init_default_rate
  is
  begin
    gv_default_rate := 0.1;
    dbms_output.put_line('init default rate to value: '||gv_default_rate);
  end init_default_rate;


begin
  dbms_output.put_line('init package ClassA');
  dbms_output.put_line('using calculator: '||ClassB.gc_calculator_version);
  init_default_rate();

end ClassA;
/

create or replace package body ClassB
is

  procedure do_calc_interest
  (
    p_amount   number
   ,p_interest out number
   ,p_tariff   varchar2 default 'DEF'
   ,p_rate     number default ClassA.get_default_rate
   ,p_comm_amt number default null
  )
  is
  begin
    --
    p_interest := p_amount*p_rate - nvl(p_comm_amt, 0);
    --
    dbms_output.put_line('do calc interest for amount='||p_amount||'; tariff='||p_tariff||'; rate='||p_rate||'; p_comm_amt='||p_comm_amt);
    --
  end;

begin
  dbms_output.put_line('init package ClassB');
  dbms_output.put_line('using default rate = '||coalesce(to_char(ClassA.get_default_rate), 'not defined'));
end ClassB;
/