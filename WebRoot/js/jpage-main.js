function jpage_main() {
	$("div.holder").jPages({
      containerID : "main_list",
      previous : "←",
      next : "→",
      perPage : 10,
      delay : 10
    });
}