export function toggleClasslist({ add, remove }) {
  document.documentElement.classList.remove(remove);
  document.documentElement.classList.add(add);
}
export function addClassnameMode() {
  if (document.documentElement.classList.contains("light")) {
    toggleClasslist({ add: 'dark', remove: 'light' });
    localStorage.setItem('lightMode', false);
  } else if (document.documentElement.classList.contains("dark")) {
    toggleClasslist({ add: 'light', remove: 'dark' });
    localStorage.setItem('lightMode', true);
  }
}