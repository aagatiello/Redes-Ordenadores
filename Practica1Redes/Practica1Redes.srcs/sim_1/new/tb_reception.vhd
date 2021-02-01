library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity tb_reception is
end tb_reception;

architecture Behavioral of tb_reception is
	component reception is
		port (
			CLK, RST, X : in std_logic;
			Y           : buffer std_logic
		);
	end component;

	signal CLK, RST, X, Y : std_logic;
begin
	DUT : reception port map(CLK, RST, X, Y);

	process begin
		CLK <= '1';
		wait for 10ns;
		CLK <= '0';
		wait for 10ns;
	end process;

	process begin
		RST <= '1';
		wait for 10ns;
		RST <= '0';
		wait;
	end process;

	process begin
		X <= '0';
		wait for 20ns;
		X <= '1';
		wait for 20ns;
		X <= '0';
		wait for 20ns;
		X <= '0';
		wait for 20ns;
		X <= '0';
		wait for 20ns;
		X <= '0';
		wait for 20ns;
		X <= '0';
		wait for 20ns;
		X <= '1';
		wait for 20ns;
		X <= '1';
		wait for 20ns;
	end process;

end Behavioral;