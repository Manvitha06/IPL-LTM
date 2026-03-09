function getValue(id) {
  const el = (typeof document !== 'undefined') ? document.getElementById(id) : null;
  return el ? String(el.value || '').trim() : '';
}

/**
 * LOGIN
 * - loginUsername: required
 * - loginPassword: required
 * -> On success: ONE single log line:
 * "Login clicked. Username: <username>, Password: <password>"
 */
function login() {
  const username = getValue('loginUsername');
  const password = getValue('loginPassword');

  if (!username) {
    console.error('Login validation failed: Username cannot be empty');
    return;
  }
  if (!password) {
    console.error('Login validation failed: Password cannot be empty');
    return;
  }

  // EXACT single log expected by the test
  console.log(`Login clicked. Username: ${username}, Password: ${password}`);
}

/**
 * REGISTER (kept from previous passing version)
 * - All fields required
 * - Valid email
 * - Username alphanumeric
 * - Password >= 8, one uppercase, one digit
 * -> On success: ONE single log line:
 * "Register clicked. Name: <name>, Email: <email>, Username: <username>, Password: <password>"
 */
function register() {
  const name = getValue('registerName');
  const email = getValue('registerEmail');
  const username = getValue('registerUsername');
  const password = (typeof document !== 'undefined' && document.getElementById('registerPassword'))
    ? String(document.getElementById('registerPassword').value || '')
    : '';

  if (!name || !email || !username || !password) {
    console.error('Registration validation failed: All fields are mandatory');
    return;
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    console.error('Registration validation failed: Invalid email address');
    return;
  }

  const usernameRegex = /^[a-zA-Z0-9]+$/;
  if (!usernameRegex.test(username)) {
    console.error('Registration validation failed: Username contains special characters');
    return;
  }

  const passwordRegex = /^(?=.*[A-Z])(?=.*\d).{8,}$/;
  if (!passwordRegex.test(password)) {
    console.error('Registration validation failed: Weak password');
    return;
  }

  // EXACT single log expected by the test
  console.log(
    `Register clicked. Name: ${name}, Email: ${email}, Username: ${username}, Password: ${password}`
  );
}

// Export for test runner
try {
  if (typeof module !== 'undefined' && module.exports) {
    module.exports = { login, register };
  }
} catch (_) {
  // ignore in browser
}